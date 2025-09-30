export interface Project {
  id?: number;
  title: string;
  description: string;
  technologies?: string;
  githubUrl?: string;
  liveUrl?: string;
  imageUrl?: string;
  createdDate?: string;
  isFeatured?: boolean;
}
